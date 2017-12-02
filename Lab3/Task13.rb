n, k = File.open('num2perm.in', 'r').readline.split(' ').map(&:to_i)
out = File.open('num2perm.out', 'w')

res = Array.new(n)

def fact(x)
  (1..x).inject(:*) || 1
end

free = Array(1..n)
(0..n - 1).each do |i|
  f = fact(n - i - 1)
  idx = k / f
  res[i] = free[idx]
  free.delete_at(idx)
  k -= f * idx
end

out.puts res.join(' ')