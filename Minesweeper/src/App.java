import java.util.*;

public class App {
    static int totCount;
    private enum level {LOW,MEDIUM,HIGH}
    public static void main(String[] args) {
            System.out.println("Welcome to Minesweeper Game");
            try (Scanner sc = new Scanner(System.in)) {
            char[][] board;

            System.out.print("Enter the number of rows:");
            int totrow = sc.nextInt();
            System.out.print("Enter the number of cols:");
            int totcol = sc.nextInt();
            System.out.println("Enter the difficulty level. 1.Low,2.Medium,3.High");
            int lenelno = sc.nextInt();
            level levelStr = level.LOW;
            switch (lenelno) {
                case 1:
                    levelStr = level.LOW;
                    break;
                case 2:
                    levelStr = level.MEDIUM;
                    break;
                case 3:
                    levelStr = level.HIGH;
                    break;
                default:
                    levelStr = level.LOW;
                    break;
            }
            board = generateInput(totrow,totcol,levelStr);
            int n = board.length,m = board[0].length;
            char[][] result = new char[n][m];
            totCount = 0;
            for(int i=0;i<n;i++)
                for(int j=0;j<m;j++)
                {
                    result[i][j] = '#';
                    if(board[i][j] != '*')
                        totCount++;
                }
        
            while(true)
            {
                try
                {            
                    displayGrid(result);
                    System.out.print("Enter the row:");
                    int row = sc.nextInt();
                    System.out.print("Enter the col:");
                    int col = sc.nextInt();
                    makeMove(board,result,row,col);
                }
                catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                    if(ex.getMessage().equals("Game Over"))
                        return;
                }
                if(totCount == 0)
                {
                    System.out.println("Won The Game!!!");
                    return;
                }
            }
        }
    }

    private static char[][] generateInput(int row, int col, level levelStr) {
        char[][] board = new char[row][col];
        int levelNo = 0;
        if(levelStr == level.LOW)
            levelNo = 6;
        else if(levelStr == level.MEDIUM)
            levelNo = 5;
        else if(levelStr == level.HIGH)
            levelNo = 4;
        int mines = row * col /levelNo;
        for(int i=0;i<row;i++)
            Arrays.fill(board[i],'#');
        Random random = new Random();
        for(int i=0;i<mines;)
        {
            int r = random.nextInt(row);
            int c = random.nextInt(col);
            if(board[r][c] == '#')
            {
                board[r][c] = '*';
                i++;
            }
        }
        return  board;
    }

    private static void makeMove(char[][] board,char[][] result, int row, int col) throws Exception {
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        int n = board.length,m = board[0].length;

        if(row < 0 || row >= n || col < 0 || col > m)        
            throw new Exception("Invalid Row/Column");
        else if(result[row][col] != '#')
        {
            System.out.println("Move already Made");
            return;
        }            
        if(board[row][col] == '*')
        {
            finalGrid(result,board);
            throw new Exception("Game Over");
        }
        else
        {
            int countNei = calculateNei(board,row,col);
            result[row][col] = (char) ('0' + countNei) ;
            totCount--;
            Queue<int[]> q = new LinkedList<>();
            if(countNei == 0)
                q.add(new int[]{row,col});
            while(!q.isEmpty())
            {
                int[] node = q.remove();
                System.out.println(node[0]+","+node[1]);
                for(int k=0;k<8;k++)
                {
                    int i = node[0] + dx[k];
                    int j = node[1] + dy[k];
                    if(i>=0 && i<n && j>=0 && j<m && (result[i][j] == '#' && board[i][j] != '*'))
                    {
                        countNei = calculateNei(board, i, j);
                        
                        result[i][j] = (char) ('0' + countNei) ;
                        totCount--;
                        if(countNei == 0)
                            q.add(new int[]{i,j});
                    }
                }               

            }
        }
    }

    private static int calculateNei(char[][] board, int row, int col) {
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};
        int n = board.length,m = board[0].length;
        int count = 0;
        for(int k=0;k<8;k++)
        {
            int i = row + dx[k];
            int j = col + dy[k];
            if(i>=0 && i<n && j>=0 && j<m && board[i][j] == '*')
                count++;
        }
        return count;
    }

    private static void displayGrid(char[][] result) {
        int n = result.length,m = result[0].length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void finalGrid(char[][] result,char[][] board)
    {
        int n = result.length,m = result[0].length;
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                if(board[i][j] == '*')
                    result[i][j] = '*';
        displayGrid(result);
    }

}
