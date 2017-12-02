import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

/**
 * @author Egor Zhdan
 */
object Input {
    private var tokenizer = StringTokenizer("")

    fun nextInt(): Int {
        if (!tokenizer.hasMoreTokens()) tokenizer = StringTokenizer(readLine()!!)
        return tokenizer.nextToken().toInt()
    }
}


class Node(val inputs: Array<Node>, private val results: BooleanArray = booleanArrayOf()) {
    var value = false

    fun isLeaf() = inputs.isEmpty()

    fun execute(): Boolean {
        if (isLeaf()) {
            return value
        }

        return results[inputs.map { if (it.execute()) 1 else 0 }.reduce { acc, it -> acc * 2 + it }]
    }

    fun depth(): Int {
        if (isLeaf()) return 0
        return 1 + (inputs.map { it.depth() }.max() ?: 0)
    }
}

fun main(args: Array<String>) {
    val n = Input.nextInt()
    val nodes = ArrayList<Node>(n)
    for (i in 0 until n) {
        val m = Input.nextInt()
        if (m == 0) {
            nodes.add(Node(emptyArray()))
        } else {
            val inputs = Array(m, { nodes[Input.nextInt() - 1] })
            val outputs = BooleanArray(1 shl m, { Input.nextInt() == 1 })
            nodes.add(Node(inputs, outputs))
        }
    }

    val exit = nodes.last()
    val leafs = nodes.filter { it.isLeaf() }
    val sb = StringBuilder()
    sb.append(exit.depth()).append('\n')

    for (mask in 0 until (1 shl leafs.size)) {
        leafs.forEachIndexed { index, node -> node.value = ((mask shr (leafs.size - 1 - index)) and 1) > 0 }
        sb.append(if (exit.execute()) 1 else 0)
    }
    println(sb)
}
