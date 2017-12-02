import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("chaincode.in").bufferedReader().readLine().toInt()
    val res = ArrayList<String>()
    val set = HashSet<String>()
    res.add(Array(n, { "0" }).joinToString(""))
    set.add(res.last())

    while (true) {
        val next1 = res.last().drop(1) + "1"
        if (next1 !in set) {
            res.add(next1)
            set.add(next1)
        } else {
            val next0 = res.last().drop(1) + "0"
            if (next0 !in set) {
                res.add(next0)
                set.add(next0)
            } else break
        }
    }

    File("chaincode.out").bufferedWriter().use { it.write(res.joinToString("\n")) }
}