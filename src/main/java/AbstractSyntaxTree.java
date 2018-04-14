import java.util.List;

/**
 * @author Johannes Ernstsen, Kristian Cordes-Andersen
 */
class AbstractSyntaxTree {

}

class Exp extends AbstractSyntaxTree {

}

class ForwardExp extends Exp {}

class TurnLeft extends Exp {}

class TurnRight extends Exp {}

class Reverse extends Exp {}

class ForLoopExp extends Exp {
	private List<Exp> body;
	private int iterations;

	public ForLoopExp(int iter, List<Exp> body) {
		this.body = body;
		iterations = iter;
	}

	public List<Exp> getBody() {
		return body;
	}

	public int getIterations() {
		return iterations;
	}
}

class IntLit extends Exp {
	private int value;

	public IntLit(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}