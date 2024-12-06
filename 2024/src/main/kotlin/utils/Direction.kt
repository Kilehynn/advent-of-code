package utils

enum class DIRECTION(val x: Int, val y: Int) {
    FORWARD(1,0),
    BACKWARD(-1,0),
    UPWARD(0,-1),
    DOWNWARD(0,1),
    TOP_RIGHT(1,-1),
    TOP_LEFT(-1,-1),
    BOTTOM_RIGHT(1,1),
    BOTTOM_LEFT(-1,1);
}