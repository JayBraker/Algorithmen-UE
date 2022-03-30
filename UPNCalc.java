import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.HashMap;

class UPNCalc {
	public static String VALREX = "\\s+[a-zA-Z0-9](\\s+[a-zA-Z0-9]\\s+[*/+-])?$";
	public static String VALSETREX = "^\\w+\\s*=\\s*[a-zA-Z0-9](\\s*[a-zA-Z0-9]\\s*[*/+-])?$";
	private HashMap<String, Double> mapVars;
	private ArrayDeque<Double> stack;

	public UPNCalc() {
		mapVars = new HashMap<>();
		stack = new ArrayDeque<>();
	}

	public double eval(String exprUPN) {
		Scanner sc = new Scanner(exprUPN);
		while(sc.hasNext()) {
			if (sc.hasNextDouble()) {
				stack.push(sc.nextDouble());
			} else {
				String sign = sc.next();
				switch (sign.trim()) {
					case ("+"): {
						stack.push(stack.pop() + stack.pop());
						break;
					}
					case ("-"): {
						double d1 = stack.pop();
						stack.push(stack.pop() - d1);
						break;
					}
					case ("*"): {
						stack.push(stack.pop() * stack.pop());
						break;
					}
					case ("/"): {
						double d1 = stack.pop();
						if (d1 == 0)
							return Double.NaN;
						stack.push(stack.pop() / d1);
						break;
					}
					default: {
						if (mapVars.containsKey(sign))
							stack.push(mapVars.get(sign));
						else
							return Double.NaN;
					}
				}
			}
		}
		return stack.isEmpty() ? Double.NaN : stack.pop();
	}

	public String analyze(String expr) {
		double value;
		String key;
		if (expr.matches(Calc.VALSETREX)) {
			String [] parts = expr.split("=");
			key = parts[0].trim();
			value = eval(parts[1]);
			mapVars.put(key, value);
		} else
			value = eval(expr);

		return String.format("result: %.1f", value);
	}

	public void interactive() {
		Scanner in=new Scanner(System.in);
		String z = "";
		while (true) {
		z = in.nextLine();
		if (z.equals("quit")) {
			break;
		}
		if (z.equals("clear")) {
			mapVars.clear();
		}
		System.out.println("task '"+z+"' => "+analyze(z));
	}
}

	public static void main(String[] args) {
		Calc c = new Calc();
		c.interactive();
	}
}
