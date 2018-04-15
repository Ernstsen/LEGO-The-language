import cv2
import numpy as np
from enum import Enum
import json
import socket
import argparse

BLUE = (np.array([70,0,0]), np.array([255,100,100]))
GREEN =  (np.array([0,0,0]), np.array([80,255,70]))
RED = (np.array([0,0,110]), np.array([92,85,255]))

argparser = argparse.ArgumentParser(description='Recognize LEGO Bricks and pass them to the EV3')
argparser.add_argument('path', metavar='P', type=str, help='the image to process')
arg = vars(argparser.parse_args())

class Brick():
	def __init__(self, color):
		self.color = color

class BrickColor(Enum):
	RED = 1
	GREEN = 2
	BLUE = 3

def show(img):
	cv2.imshow("image", img)
	cv2.waitKey(0)
	cv2.destroyAllWindows()

def rescale(img):
	test = cv2.resize(img, (0,0), fx=0.05, fy=0.05)
	mask = cv2.resize(test, (0,0), fx=20, fy=20)
	return mask

def find(img, color):
	ret_img = None
	if color == 'blue':
		ret_img = cv2.inRange(img, BLUE[0], BLUE[1])
	elif color == "green":
		ret_img = cv2.inRange(img, GREEN[0],GREEN[1])
	elif color == "red":
		ret_img = cv2.inRange(img, RED[0], RED[1])
	return rescale(ret_img)

def compress(arr):
	retarr = []
	current = 'T'
	for a in arr:
		if a != current:
			current = a
			retarr.append(a)

	retarr = [x for x in retarr if x != 'p']
	return retarr

def send(arr):
	start_string = '{"Bricks": ['
	for ind, obj  in enumerate(arr):
		if obj == 'b':
			start_string += '{"color" : "Blue"}'
		elif obj == 'g':
			start_string += '{"color" : "Green"}'
		elif obj == 'r':
			start_string += '{"color" : "Red"}'
		if ind != len(arr) - 1:
			start_string += " , "
	
	start_string += "]}"
	print(start_string)
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	s.connect(("192.168.2.2", 8192))
	s.sendall(start_string.encode())
	s.close()

def generate(red, green, blue):
	arr = []
	ran = range(247,256)
	for i, _ in enumerate(red):
		if any([x for x in ran if x in red[i]]):
			arr.append('r')
		elif any([x for x in ran if x in blue[i]]):
			arr.append('b')
		elif any([x for x in ran if x in green[i]]):
			arr.append('g')
		else:
			arr.append('p')

	return compress(arr)

def main():
	path = arg['path']
	print('Resizing image')
	img = cv2.imread(path,cv2.IMREAD_COLOR)
	# resize = cv2.resize(img, (0,0), fx=0.1, fy=0.1)
	print('Finding red bricks')
	red = find(img, 'red')
	print('Finding green bricks')
	green = find(img, 'green')
	print('Finding blue bricks')
	blue = find(img, 'blue')
	arr = generate(red, green, blue)
	cv2.imwrite('red.png',red)
	cv2.imwrite('green.png',green)
	cv2.imwrite('blue.png',blue)
	print('Sending JSON')
	send(arr)

if __name__ == '__main__':
	main()
