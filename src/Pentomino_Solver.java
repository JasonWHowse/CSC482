import java.util.*;
public class Pentomino_Solver {
    private ArrayList<Board> finishedBoards = new ArrayList<>();;

    public Pentomino_Solver(int x, int y, Shape[] shapeList, boolean showProgress){
        this(new Board(x, y), shapeList, showProgress);
    }

    public Pentomino_Solver(Board board, Shape[] shapeList, boolean showProgress) {
        this.solveBoard(board, shapeList, showProgress);
        this.cleanUpBoard();
    }

    private Pentomino_Solver cleanUpBoard() {
        ArrayList<Board> newboards = new ArrayList<>();
        if(finishedBoards.size()>0){
            newboards.add(this.finishedBoards.get(0));
        }else{
            return this;
        }
        for(int i = 1; i< finishedBoards.size(); i++){
            if(!isLikeAnItemInArray(newboards, this.finishedBoards.get(i))){
                newboards.add(this.finishedBoards.get(i));
            }
        }

        finishedBoards = newboards;
        return this;
    }

    private boolean isLikeAnItemInArray(ArrayList<Board> aB, Board cb){
        for(Board board: aB){
            for(int k = 0; k<2; k++) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        board = rotateBoard(board);
                        if (board.equals(cb)) {
                            return true;
                        }
                    }
                    board = mirrorBoard(board);
                }
                board = mirrorVBoard(board);
            }
        }
        return false;
    }

    private Board mirrorBoard(Board board) {
        int nxm = board.getBoard().length;
        int nym = board.getBoard()[0].length;
        char[][] nB = new char[nxm][];
        for(int x=0; x<nB.length;x++){
            nB[x] = new char[nym];
            for(int y=0; y<nB[x].length; y++){
                nB[x][y] = board.getBoard()[nxm-x-1][y];
            }
        }
        return new Board(nB, board.getUsedPieces());
    }

    private Board mirrorVBoard(Board board) {
        int nxm = board.getBoard().length;
        int nym = board.getBoard()[0].length;
        char[][] nB = new char[nxm][];
        for(int x=0; x<nB.length;x++){
            nB[x] = new char[nym];
            for(int y=0; y<nB[x].length; y++){
                nB[x][y] = board.getBoard()[x][nym-1-y];
            }
        }
        return new Board(nB, board.getUsedPieces());
    }

    private Board rotateBoard(Board board) {
        int nxm = board.getBoard()[0].length;
        int nym = board.getBoard().length;
        char[][] nB = new char[nxm][];
        for(int x=0; x<nB.length;x++){
            nB[x] = new char[nym];
            for(int y=0; y<nB[x].length; y++){
                nB[x][y] = board.getBoard()[y][x];
            }
        }
        return new Board(nB, board.getUsedPieces());
    }

    private Pentomino_Solver solveBoard(Board baseboard, Shape[] shapeList, boolean showProgress){
        ArrayList<Board> incomingBoardList = new ArrayList<>();
        incomingBoardList.add(baseboard);
        ArrayList<Board> outgoingBoardList = new ArrayList<>();
        for(Shape shape: shapeList){
            if(showProgress) {
                System.out.println(shape.getShapeNumber() + ": " + incomingBoardList.size());
            }
            for(int x=0; x<baseboard.getBoard().length; x++){
                for(int y=0; y<baseboard.getBoard()[x].length; y++){
                    for(Board board: incomingBoardList){
                        for(Board board2: board.placeShape(x,y, shape)){
                            outgoingBoardList.add(board2);
                            if(board2.isBoardComplete()){
                                this.finishedBoards.add(board2);
                            }
                        }
                    }
                }
            }
            incomingBoardList = outgoingBoardList;
            outgoingBoardList = new ArrayList<>();
        }
        return this;
    }

    public static void main(String[] args) {
        Shape[] s = DefaultShapes.getShapeList();
        System.out.println(new Pentomino_Solver(3, 20, s, true));
//        System.out.println(new Pentomino_Solver(4, 15, s, true));
//        System.out.println(new Pentomino_Solver(5, 12, s, true));
//        System.out.println(new Pentomino_Solver(6, 10, s, true));
        char[][] oddBoard = new Board(8,8).getBoard();
        oddBoard[2][2] = '-';
        oddBoard[2][5] = '-';
        oddBoard[5][5] = '-';
        oddBoard[5][2] = '-';
//        System.out.println(new Pentomino_Solver(new Board(oddBoard, new HashSet<>()), s, true));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("List of "+this.finishedBoards.size()+" finished boards:\n");
        for(Board board: finishedBoards){
            sb.append(board + "\n");
            sb.append("\n");
        }
        return sb.toString();
    }
}

class Board implements Cloneable {
    private char[][] board;
    private HashSet<Character> usedPieces = new HashSet<>();
    private char blankChar = '█';
    private int minPieceSize = 5;

    private Shape[] shapes = null;

    public Board(int x, int y){
        this.board = new char[x][];
        for (int x1 = 0; x1 < x; x1++) {
            this.board[x1] = new char[y];
            for (int y1 = 0; y1 < y; y1++) {
                this.board[x1][y1] = blankChar;
            }
        }
    }

    public Board(char[][] board,HashSet<Character> usedPieces){
        this.board=board;
        this.usedPieces = usedPieces;
    }

    public Board(char[][] board){
        this.board=board;
    }

    public HashSet<Character> getUsedPieces() {
        return usedPieces;
    }

    public Board setBlankChar(char ch){
        this.blankChar = ch;
        for(int i = 0; i<this.board.length;i++){
            for(int j = 0; j<this.board[i].length; j++){
                if(blankChar==this.board[i][j]){
                    this.board[i][j]=ch;
                }
            }
        }
        return this;
    }

    public Board[] placeShape(int x, int y, Shape shape){
        ArrayList<Board> newBoards = new ArrayList<>();
        for(int i = 0; i< shape.getShapeOptions().length; i++){
            try {
                Board newBoard = this.clone();
                newBoard.placeShape(x, y, shape.getShapeOptions()[i], shape.getShapeNumber());
                if(newBoard.board!=null){
                    newBoards.add(newBoard);
                }
            }catch (CloneNotSupportedException e) {}
        }
        Board[] outBoard = new Board[newBoards.size()];
        for(int i = 0; i<outBoard.length;i++){
            outBoard[i] = newBoards.get(i);
        }
        return outBoard;
    }

    private Board placeShape(int x, int y, short[][] shape, char shapeV){
        if(this.canPlaceShape(x,y,shape) && !this.usedPieces.contains(shapeV)){
            for(short[] node: shape){
                this.board[x+node[0]][y+node[1]]=shapeV;
            }
            this.usedPieces.add(shapeV);
        }else{
            board = null;
        }
        return this;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isBoardComplete(){
        for (char[] line: board){
            for(char ch: line){
                if(ch==blankChar){
                    return false;
                }
            }
        }
        return true;
    }

    private Board rePlaceShape(int x, int y, short[][] shape, char shapeV){//for placing shapes that are already on the board
        if(this.canPlaceShape(x,y,shape)){
            for(short[] node: shape){
                this.board[x+node[0]][y+node[1]]=shapeV;
            }
            this.usedPieces.add(shapeV);
        }else{
            board = null;
        }
        return this;
    }

    private boolean canPlaceShape(int x, int y, short[][] shape){
        int maxX = 0;
        int maxY = 0;
        for(short[] node: shape){
            maxX = Math.max(maxX, node[0]);
            maxY = Math.max(maxY, node[1]);
        }
        if(this.board.length == 0 || maxY+y >= this.board[0].length || maxX+x >= this.board.length || this.checkContSpaces(x, y, shape)){
            return false;
        }else{
            for(short[] node: shape){
                if(this.board[x+node[0]][y+node[1]]!=blankChar){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean checkContSpaces(int x1, int y1, short[][] shape) {
        HashSet<String> cS = new HashSet<>();
        Deque<int[]> sN = new ArrayDeque<>();
        Deque<int[]> sN2 = new ArrayDeque<>();
        for (short[] node : shape) {
            cS.add((x1 + node[0]) + "," + (y1 + node[1]));
        }
        for (short[] node : shape) {
            int x = node[0]+x1;
            int y = node[1]+y1;
            int xU = x+1;
            int xD = x-1;
            int yU = y+1;
            int yD = y-1;
            if(x>0 && !cS.contains(xD + "," + y) && this.board[xD][y]==this.blankChar){
                sN.addLast(new int[]{xD, y});
            }
            if(y>0 && !cS.contains(x + "," + yD) && this.board[x][yD]==this.blankChar){
                sN.addLast(new int[]{x, yD});
            }
            if(xU<this.board.length && !cS.contains(xU + "," + y) && this.board[xU][y]==this.blankChar){
                sN.addLast(new int[]{xU, y});
            }
            if(yU<this.board[0].length && !cS.contains(x + "," + yU) && this.board[x][yU]==this.blankChar){
                sN.addLast(new int[]{x, yU});
            }
        }

        int count = 1;

        while(sN.size()!=0){
            sN2.add(sN.pop());
            while(sN2.size()!=0){
                x1 = sN2.peek()[0];
                y1 = sN2.peek()[1];
                if(cS.contains(x1 + "," + y1) && count==1){
                    count = this.minPieceSize;
                    sN2 = new ArrayDeque<>();
                    break;
                }
                int xU = x1+1;
                int xD = x1-1;
                int yU = y1+1;
                int yD = y1-1;
                if(x1>0 && !cS.contains(xD + "," + y1) && this.board[xD][y1]==this.blankChar){
                    sN2.addLast(new int[]{xD, y1});
                    cS.add(xD + "," + y1);
                    count++;
                }
                if(y1>0 && !cS.contains(x1 + "," + yD) && this.board[x1][yD]==this.blankChar){
                    sN2.addLast(new int[]{x1, yD});
                    cS.add(x1 + "," + yD);
                    count++;
                }
                if(xU<this.board.length && !cS.contains(xU + "," + y1) && this.board[xU][y1]==this.blankChar){
                    sN2.addLast(new int[]{xU, y1});
                    cS.add(xU + "," + y1);
                    count++;
                }
                if(yU<this.board[0].length && !cS.contains(x1 + "," + yU) && this.board[x1][yU]==this.blankChar){
                    sN2.addLast(new int[]{x1, yU});
                    cS.add(x1 + "," + yU);
                    count++;
                }
                cS.add(x1 + "," + y1);
                sN2.pop();
            }

            if (count%this.minPieceSize != 0) {
                return true;
            }
//            if (count < this.minPieceSize) {
//                return true;
//            }
            count = 1;
        }
        return false;
    }

    public Board clone() throws CloneNotSupportedException{
        char[][] newBoard = new char[this.board.length][];
        for(int i = 0; i< board.length; i++){
            newBoard[i] = this.board[i].clone();
        }
        Board newB =new Board(newBoard, new HashSet<>(this.usedPieces));
        newB.minPieceSize = this.minPieceSize;
        newB.blankChar = this.blankChar;
        return newB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        return Arrays.deepEquals(getBoard(), ((Board) o).getBoard());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getBoard());
    }

    public Board setDefaultPieces(){
        return this.addPieces(DefaultShapes.getShapeList());
    }

    public Board addPieces(Shape[] shapes){
        if(this.shapes == null){
            this.shapes = shapes;
        }else{
            Shape[] newShapes = new Shape[shapes.length + this.shapes.length];
            for(int i = 0; i < shapes.length; i++){
                newShapes[i] = shapes[i];
            }
            for(int i = 0; i < this.shapes.length; i++){
                newShapes[i+shapes.length] = this.shapes[i];
            }
            this.shapes = newShapes;
        }
        return this;
    }

    public Board addPiece(Shape shape){
        return this.addPieces(new Shape[]{shape});
    }

    public Shape[] getShapes() {
        return shapes;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (char[] i: this.board) {
            for(char j: i){
//                output.append(j + ", ");
                output.append(j);
            }
//            output.setLength(output.length() - 2);
            output.append("\n");
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }

    public String toStringPieces(boolean includeDisplay){
        StringBuilder output = new StringBuilder();
        for(Shape sh : this.shapes){
            output.append(sh);
            output.append("\n");
            if(includeDisplay) {
                output.append(sh.displayShapes());
                output.append("\n");
            }
        }
        return output.toString();
    }

    public String toStringPieces(){
        return this.toStringPieces(false);
    }
}

class Shape {

    private final char shapeNumber;
    private short[][][] shapeOptions;

    public Shape(char shapeNumber, short[][] baseShape) {
        this.shapeNumber = shapeNumber;
        this.setShapeOptions(baseShape);
    }

    public short[][][] getShapeOptions() {
        return this.shapeOptions;
    }

    public char getShapeNumber() {
        return shapeNumber;
    }

    private Shape setShapeOptions(short[][] baseShape) {
        short[][] shap = baseShape;
        Deque<short[][]> sL = new ArrayDeque<>();
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                shap = this.rotateShape(shap);
                if (this.hasNoSameShapes(sL, shap)){
                    sL.add(shap);
                }
            }
            shap = this.mirrorShape(shap);
        }
        this.shapeOptions = new short[sL.size()][][];
        for(int i = 0; i<shapeOptions.length; i++){
            shapeOptions[i] = sL.pop();
        }
        return this;
    }

    private short[][] rotateShape(short[][] baseShape){
        short[][] newShape = new short[baseShape.length][];
        if(baseShape.length==0){
            return newShape;
        }
        short length = 0;
        for(short[] i : baseShape){
            length = (short)Math.max(Math.max(length, i[0]), i[1]);
        }
        for(int i = 0; i < baseShape.length; i++){
            newShape[i] = new short[]{(short) ((length) - baseShape[i][1]), baseShape[i][0]};
        }
        short minX = newShape[0][0];
        short minY = newShape[0][1];

        for(short[] i : newShape){
            minX = (short)Math.min(minX, i[0]);
            minY = (short)Math.min(minY, i[1]);
        }
        for(int i = 0; i< newShape.length; i++){
            newShape[i][0] = (short)(newShape[i][0]-minX);
            newShape[i][1] = (short)(newShape[i][1]-minY);
        }

        return newShape;
    }

    private short[][] mirrorShape(short[][] baseShape){
        short[][] mirrorshape = new short[baseShape.length][];
        short max = 0;
        for(short[] i : baseShape){
            max = (short)Math.max(max, i[0]);
        }
        for(int i = 0; i<baseShape.length; i++){
            mirrorshape[i] = new short[]{(short)(max-baseShape[i][0]), baseShape[i][1]};
        }
        return mirrorshape;
    }

    private boolean hasNoSameShapes(Deque<short[][]> shapelist, short[][] proposedShape){
        for(short[][] shape: shapelist){
            if(hasNoSameShapes(shape, proposedShape)){
                return false;
            }
        }
        return true;
    }

    private boolean hasNoSameShapes(short[][] first, short[][] second){
        if(second.length!=first.length || first.length==0){
            return false;
        }
        for (short[] node1: first) {
            boolean hasMatchingNode = false;
            for(short[] node2: second){
                hasMatchingNode = hasMatchingNode || (node1[0]==node2[0] && node1[1]==node2[1]);
            }
            if(!hasMatchingNode){
                return false;
            }
        }
        return true;
    }

    public String displayShapes(){
        int max=0;

        for(short[] node: this.shapeOptions[0]){
            max = (short)Math.max(Math.max(max, node[0]), node[1]);
        }

        char[][][] fields = new char[this.shapeOptions.length][][];
        for(int z = 0; z<this.shapeOptions.length; z++) {
            fields[z] = new char[max+1][];
            for (int x = 0; x <= max; x++) {
                fields[z][x] = new char[max+1];
                for (int y = 0; y <= max; y++) {
                    fields[z][x][y] = ' ';
                }
            }
        }

        StringBuilder output = new StringBuilder();
        int count = 0;
        for(int i = 0; i<this.shapeOptions.length;i++){
            for(short[] node: this.shapeOptions[i]){
                fields[i][(int)node[0]][(int)node[1]] = this.shapeNumber;
            }
            output.append("Rotation: " + count++ + "\n");
            for(char[] line : fields[i] ){
                output.append(new String(line) + "\n");
            }
            output.append("\n");
        }

        return output.toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Shape: " + shapeNumber + "\n");
        for( short[][] shape: shapeOptions) {
            for (short[] node : shape) {
                output.append("(" + node[0] + "," + node[1] + "),");
            }
            output.setLength(output.length() - 1);
            output.append("\n");
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }
}

class DefaultShapes{
    public static Shape[] getShapeList(){
        return DefaultShapes.shapeList;
    }
    final private static Shape[] shapeList = new Shape[]{
            new Shape( 'W',//W
                    new short[][]{
                            new short[]{0,0},
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{1,2},
                            new short[]{2,2}
                    }
            ),
            new Shape( 'Y',//Y
                    new short[][]{
                            new short[]{2,0},
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{2,1},
                            new short[]{3,1}
                    }
            ),
            new Shape( 'T',//T
                    new short[][]{
                            new short[]{0,0},
                            new short[]{1,0},
                            new short[]{2,0},
                            new short[]{1,1},
                            new short[]{1,2}
                    }
            ),
            new Shape( 'U',//U
                    new short[][]{
                            new short[]{0,0},
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{2,1},
                            new short[]{2,0}
                    }
            ),
            new Shape( 'V',//V
                    new short[][]{
                            new short[]{0,0},
                            new short[]{1,0},
                            new short[]{2,0},
                            new short[]{0,1},
                            new short[]{0,2}
                    }
            ),
            new Shape( 'X',//X
                    new short[][]{
                            new short[]{1,0},
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{2,1},
                            new short[]{1,2}
                    }
            ),
            new Shape( 'Z',//Z
                    new short[][]{
                            new short[]{0,0},
                            new short[]{1,0},
                            new short[]{1,1},
                            new short[]{1,2},
                            new short[]{2,2}
                    }
            ),
            new Shape( 'F',//F/R
                    new short[][]{
                            new short[]{1,0},
                            new short[]{2,0},
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{1,2}
                    }
            ),
            new Shape( 'I',//I/O
                    new short[][]{
                            new short[]{0,0},
                            new short[]{1,0},
                            new short[]{2,0},
                            new short[]{3,0},
                            new short[]{4,0}
                    }
            ),
            new Shape( 'L',//L/Q
                    new short[][]{
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{2,1},
                            new short[]{3,1},
                            new short[]{3,0}
                    }
            ),
            new Shape( 'P',//P
                    new short[][]{
                            new short[]{0,0},
                            new short[]{1,0},
                            new short[]{0,1},
                            new short[]{1,1},
                            new short[]{0,2}
                    }
            ),
            new Shape( 'N',//N/S
                    new short[][]{
                            new short[]{0,0},
                            new short[]{1,0},
                            new short[]{1,1},
                            new short[]{2,1},
                            new short[]{3,1}
                    }
            ),
    };
//    final private static Shape[] shapeList2 = new Shape[]{
//            new Shape( 'U',//U
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{2,1},
//                            new short[]{2,0}
//                    }
//            ),
//            new Shape( 'X',//X
//                    new short[][]{
//                            new short[]{1,0},
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{2,1},
//                            new short[]{1,2}
//                    }
//            ),
//            new Shape( 'I',//I/O
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{1,0},
//                            new short[]{2,0},
//                            new short[]{3,0},
//                            new short[]{4,0}
//                    }
//            ),
//            new Shape( 'T',//T
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{1,0},
//                            new short[]{2,0},
//                            new short[]{1,1},
//                            new short[]{1,2}
//                    }
//            ),
//            new Shape( 'V',//V
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{1,0},
//                            new short[]{2,0},
//                            new short[]{0,1},
//                            new short[]{0,2}
//                    }
//            ),
//            new Shape( 'W',//W
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{1,2},
//                            new short[]{2,2}
//                    }
//            ),
//            new Shape( 'Y',//Y
//                    new short[][]{
//                            new short[]{2,0},
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{2,1},
//                            new short[]{3,1}
//                    }
//            ),
//            new Shape( 'Z',//Z
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{1,0},
//                            new short[]{1,1},
//                            new short[]{1,2},
//                            new short[]{2,2}
//                    }
//            ),
//            new Shape( 'F',//F/R
//                    new short[][]{
//                            new short[]{1,0},
//                            new short[]{2,0},
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{1,2}
//                    }
//            ),
//            new Shape( 'L',//L/Q
//                    new short[][]{
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{2,1},
//                            new short[]{3,1},
//                            new short[]{3,0}
//                    }
//            ),
//            new Shape( 'P',//P
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{1,0},
//                            new short[]{0,1},
//                            new short[]{1,1},
//                            new short[]{0,2}
//                    }
//            ),
//            new Shape( 'N',//N/S
//                    new short[][]{
//                            new short[]{0,0},
//                            new short[]{1,0},
//                            new short[]{1,1},
//                            new short[]{2,1},
//                            new short[]{3,1}
//                    }
//            ),
//
//    };
}
