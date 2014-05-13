package Model;

public class Chromosome {

	private static class Gene {
		private static final int GENE_LENGTH = 4;
		private String bits;

		public Gene() {
			this("");
		}

		public Gene(String bits) {
			if (bits.length() < GENE_LENGTH) {
				for (int i = 0; i < GENE_LENGTH; i++) {
					bits += SimpleLib.randMinMax(0, 2);
				}
			} else {
				bits = bits.substring(0, GENE_LENGTH - 1);
			}
		}

		public int getVal() {
			return Integer.parseInt(bits, 2);
		}
	}

	enum Operator {
		Add, Subtract, Multiply, Divide
	}

	private static final float CROSSOVER_RATE = 0.7f;
	private static final float MUTATION_RATE = 0.001f;
	private static final int CHROMOSOME_LENGTH = 300;

	private Gene[] genes; // this was originally a byte array, but it's freaking
							// difficult to write byte literals in java. Why?
							// Who knows. For now we're int-ing it up.

	public Chromosome() {
		genes = new Gene[CHROMOSOME_LENGTH];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene();
		}
	}

	public int parseGenes() {
		int result = 0;
		boolean operatorMode = false;
		Operator lastOperator = Operator.Add;
		for (int i = 0; i < genes.length; i++) {
			int currentGene = genes[i].getVal();
			if (operatorMode) {
				switch (currentGene) {
				case 10:
					lastOperator = Operator.Add;
					break;
				case 11:
					lastOperator = Operator.Subtract;
					break;
				case 12:
					lastOperator = Operator.Multiply;
					break;
				case 13:
					lastOperator = Operator.Divide;
					break;
				default:
					continue;
				}
				operatorMode = !operatorMode;
			} else {
				if (currentGene < 10) {
					switch (lastOperator) {
					case Add:
						result += currentGene;
						break;
					case Subtract:
						result -= currentGene;
						break;
					case Multiply:
						result *= currentGene;
						break;
					case Divide:
						result /= currentGene;
						break;
					default:
						continue;
					}
					operatorMode = !operatorMode;
				}
			}
		}
		return result;
	}
}
