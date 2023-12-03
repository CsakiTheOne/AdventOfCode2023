package utils

class Utils {
    companion object {

        fun IntRange.expand(value: Int): IntRange {
            return IntRange(this.first - value, this.last + value)
        }

    }
}