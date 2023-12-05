public class Assignment6{

    static boolean isSafe(int[][] board,int row,int col){
        // Check the left side of this row
        for(int i=0;i<col;i++){
            if(board[row][i] == 1){
                return false;
            }
        }

        // Check upper left side diagonal
        for(int i=row,j=col;i>=0 && j>=0;i--,j--){
            if(board[i][j] == 1) {
                return false;
            }
        }

        // Check down left side diagonal
        for(int i=row,j=col;i<board.length && j>=0;i++,j--){
            if(board[i][j] == 1){
                return false;
            }
        }

        return true;
    }

    static boolean solveNQueens(int[][] board,int col){
        if(col == board.length){
            return true;
        }

        for(int i=0;i<board.length;i++){
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if(solveNQueens(board, col+1)){
                    return true;
                }

                // If the position chosen doesnt lead to solution then backtrack
                board[i][col] = 0;
            }
        }

        return false;
        
    }

    static void printSolution(int[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 4;

        int[][] board = new int[N][N];

        if(solveNQueens(board,0)){
            printSolution(board);
        }
        else{
            System.out.println("Solution does not exist");
        }
    }
}