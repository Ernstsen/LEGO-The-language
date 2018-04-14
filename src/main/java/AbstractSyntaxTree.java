import java.util.List;

/**
 * @author Johannes Ernstsen, Kristian Cordes-Andersen
 */
class AbstractSyntaxTree {

}

class Exp extends AbstractSyntaxTree {

}

class ForwardExp extends Exp {}

class BrakeExp extends Exp {}

class TurnLeft extends Exp {}

class TurnRight extends Exp {}

class Reverse extends Exp {}

class ForLoopExp extends Exp {
	private List<Exp> body;

	public ForLoopExp(List<Exp> body) {
		this.body = body;

	}

	public List<Exp> getBody() {
		return body;
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