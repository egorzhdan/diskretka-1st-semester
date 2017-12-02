input = File.open('perm2num.in', 'r')
output = File.open('perm2num.out', 'w')
n = input.readline.to_i
p = input.readline.split(' ').map(&:to_i)

res = 0

def fact(x)
  (1..x).inject(:*) || 1
end

free = Array(1..n)
(0..n - 1).each do |i|
  f = fact(n - i - 1)
  idx = free.index(p[i])
  free.delete_at(idx)
  res += f * idx
end

output.puts res