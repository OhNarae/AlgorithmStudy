


class SnailUnit{
	int snailTest[][];
	int n;
	
	SnailUnit(int n){
		this.n = n;
		snailTest = new int[n][n];
	}
	
	void show() {
		for(int i = 0; i < n ; i++) {
			for(int j = 0 ; j < n ; j++)
				System.out.printf("%3d", snailTest[i][j]);
			System.out.println();
		}
	}
	
	//way 1:왼쪽->오른쪽 2:위->아래, 3:오른쪽->왼쪽, 4:아래->위
	void Print(int i, int j, int value, int way) {
		snailTest[i][j] = value;
		if(way == 1)
		{
			if(j+1 < n && snailTest[i][j+1] == 0)
				Print(i, j+1, value-1, way);
			else if(snailTest[i+1][j] == 0)
				Print(i+1, j, value-1, 2);
			else
				return;
		}
		else if(way == 2)
		{
			if(i+1 < n && snailTest[i+1][j] == 0)
				Print(i+1, j, value-1, way);
			else if(snailTest[i][j-1] == 0)
				Print(i, j-1, value-1, 3);
			else
				return;
		}
		else if(way == 3)
		{
			if(j-1 >= 0 && snailTest[i][j-1] == 0)
				Print(i, j-1, value-1, way);
			else if(snailTest[i-1][j] == 0)
				Print(i-1, j, value-1, 4);
			else
				return;
		}
		else if(way == 4)
		{
			if(i-1 < n && snailTest[i-1][j] == 0)
				Print(i-1, j, value-1, way);
			else if(snailTest[i][j+1] == 0)
				Print(i, j+1, value-1, 1);
			else
				return;
		}
	}
}
public class Snail {

	public static void main(String[] args) {
		int cnt = 4;
		
		SnailUnit snail = new SnailUnit(cnt);
		snail.Print(0, 0, cnt*cnt, 1);

		snail.show();
	}
}
