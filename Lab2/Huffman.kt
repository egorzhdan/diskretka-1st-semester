import java.io.File
import java.util.*

/**
 * @author Egor Zhdan
 */
class Node(val priority: Long, val idx: Int?, val left: Node? = null, val right: Node? = null)

fun main(args: Array<String>) {
    val input = File("huffman.in").bufferedReader()
    val n = input.readLine().toInt()
    val p = input.readLine().split(" ").map(String::toLong)

    val queue = PriorityQueue<Node>(Comparator { o1, o2 ->
        if (o1.priority == o2.priority) -1 else o1.priority.compareTo(o2.priority)
    })
    queue += p.mapIndexed { i, it -> Node(it, i) }
    while (queue.size > 1) {
        val n1 = queue.poll()
        val n2 = queue.poll()
        val new = Node(n1.priority + n2.priority, null, n1, n2)
        queue += new
    }

    val root = queue.poll()
    var ans = 0.toLong()
    var count = 0
    fun go(node: Node, depth: Int) {
        if (node.idx != null) {
            count++
            ans += p[node.idx] * depth
        }
        if (node.left != null) go(node.left, depth + 1)
        if (node.right != null) go(node.right, depth + 1)
    }
    go(root, 0)
    check(count == n, { "$count != $n" })

    File("huffman.out").writeText("$ans")
}
