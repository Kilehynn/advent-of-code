package utils

enum class DIRECTION(val x: Int, val y: Int) {
    RIGHT(1,0),
    LEFT(-1,0),
    UP(0,-1),
    DOWN(0,1),
    TOP_RIGHT(1,-1),
    TOP_LEFT(-1,-1),
    BOTTOM_RIGHT(1,1),
    BOTTOM_LEFT(-1,1);
}