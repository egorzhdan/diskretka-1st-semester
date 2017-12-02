input = File.open('nextchoose.in', 'r')
output = File.open('nextchoose.out', 'w')
n, k = input.readline.split(' ').map(&:to_i)
p = input.readline.split(' ').map(&:to_i)

def next_choose(p, n, k)
  c = p + [n + 1]
  idx = k - 1
  while (idx >= 0) and (c[idx + 1] - c[idx] < 2)
    idx -= 1
  end
  if idx >= 0
    c[idx] += 1
    (idx + 1..k - 1).each {|i| c[i] = c[i - 1] + 1}
    return c[0...-1]
  end
  nil
end

nxt = next_choose(p, n, k)
unless nxt.nil?
  nxt = nxt.join(' ')
end
output.puts nxt || -1