import java.util.Random;

public class Game {

	public Game() {
	}

	// ��ʼ����ͼ��������֮һ�ĵط��ǳɻ�ģ�
	public Block[][] initMap(Block[][] map, int size) {
		for (int i = 0; i < size + 2; i++) {
			for (int j = 0; j < size + 2; j++) {
				map[i][j] = new Block();
			}
		}

		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				Random random = new Random();
				int r = random.nextInt(3);
				if ((i + j) % 3 == 1)
					map[i][j].setLive(true);
				else
					map[i][j].setLive(false);
			}
		}
		return map;
	}

	// �����ͼ
	public void printMap(Block[][] map, int size) {
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				if (map[i][j].live == true)
					System.out.print("@ ");
				else if (map[i][j].live == false)
					System.out.print("O ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------");
		System.out.println();
	}

	// ��ͼ����
	public Block[][] updateMap(Block[][] map, int size) {
		// ͳ����Χ�˸���λ�ĳɻ�������temp�У���set
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				int temp = 0;
				for (int k = -1; k <= 1; k++) {
					for (int l = -1; l <= 1; l++) {
						if (map[i + k][j + l].live == true)
							temp++;
					}
				}
				if (map[i][j].live == true)
					temp = temp - 1;
				map[i][j].setNumber(temp);
			}
		}

		// ����ϸ��״̬
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				if (map[i][j].number == 3)
					map[i][j].setLive(true);
				else if (map[i][j].number != 2 && map[i][j].number != 3)
					map[i][j].setLive(false);
			}
		}
		return map;
	}

	public static void main(String[] args) {
		Game game = new Game();
		int size = 20;
		Block[][] map;
		map = new Block[size + 2][size + 2];
		// ��ʼ����ͼ
		map = game.initMap(map, size);
		// ���ԭʼ��ͼ
		game.printMap(map, size);

		for (int flag = 0; flag < 100; flag++) {
			// ���µ�ͼ
			map = game.updateMap(map, size);
			// �ٴ������ͼ
			game.printMap(map, size);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.exit(0);
			}
		}
	}
}
