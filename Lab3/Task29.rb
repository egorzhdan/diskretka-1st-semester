input = File.open('nextpartition.in', 'r')
output = File.open('nextpartition.out', 'w')
line = input.readline
n, comps = line.split('=')
n = n.to_i
p = comps.split('+').map(&:to_i)

if p == [n]
  output.puts 'No solution'
  Process.exit 0
end

def next_partition(p)
  p[-2] += 1
  p[-1] -= 1
  if p.size >= 2 and p[-2] > p[-1]
    p[-2] += p[-1]
    p.pop
    return p
  end
  while p.size >= 2 and p[-1] >= p[-2] * 2
    p.push(p[-1] - p[-2])
    p[-2] = p[-3]
  end
  p
end

prv = next_partition(p)
output.puts n.to_s + '=' + prv.join('+')
