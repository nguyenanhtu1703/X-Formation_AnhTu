package prac.xformation.anhtu;

public class MineSweeperImp implements MineSweeper {
	private String[] rows = null;
	
	@Override
	public void setMineField(String mineField) throws IllegalArgumentException {
		rows = mineField.split("\n");
		
		for (String s : rows) {
			
			/* check if the input char is a character or a mine */
			boolean isAllCharValid = s.codePoints().anyMatch(x -> ((char) x != '.' && (char) x != '*'));
			if (isAllCharValid) {
				throw new IllegalArgumentException();
			}

			/* check if the input string is valid as a square or not */
			if (s.length() != rows[0].length()) {
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public String getHintField() throws IllegalStateException {
		if (rows == null) {
			throw new IllegalStateException();
		} else {
			int[] mineCounterArray[] = new int[rows.length][];
			mineCounterArray[0] = new int[rows[0].length()];
			
			for (int i = 0; i < rows.length; i++) {
				if (i < rows.length - 1) {
					mineCounterArray[i + 1] = new int[rows[0].length()];
				}
				for (int j = 0; j < rows[i].length(); j++) {
					if (rows[i].charAt(j) == '*') {
						if (i > 0) {
							mineCounterArray[i - 1][j]++;
						}
						if (i < rows.length - 1) {
							mineCounterArray[i + 1][j]++;
						}	
						if (j > 0) {
							mineCounterArray[i][j - 1]++;
						}	
						if (j < rows[0].length() - 1) {
							mineCounterArray[i][j + 1]++;
						}
						if (i > 0 && j > 0) {
							mineCounterArray[i - 1][j - 1]++;
						}
						if (i > 0 && j < rows[0].length() - 1) {
							mineCounterArray[i - 1][j + 1]++;
						}
						if (i < rows.length - 1 && j < rows[0].length() - 1) {
							mineCounterArray[i + 1][j + 1]++;
						}
						if (i < rows.length - 1 && j > 0) {
							mineCounterArray[i + 1][j - 1]++;
						}
					}
				}
			}
			
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < mineCounterArray.length; i++) {
				for (int j = 0; j < mineCounterArray[i].length; j++) {
					if (rows[i].charAt(j) == '*')
					{
						result.append('*');
					} else {
						result.append((char) (mineCounterArray[i][j] + (int) '0'));
					}
				}
				if (i < mineCounterArray.length - 1) {
					result.append('\n');
				}
			}
			return result.toString();
		}
	}

}
