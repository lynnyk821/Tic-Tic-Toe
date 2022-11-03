class Player {
    protected final int x, y;
    protected Player(int[] coordinate)
    {
        this.x = coordinate[0];
        this.y = coordinate[1];
    }
    public String toString() { return x + " " + y; }
}
