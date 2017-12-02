input = File.open('choose2num.in', 'r')
output = File.open('choose2num.out', 'w')
n, k = input.readline.split(' ').map(&:to_i)
s = input.readline.split(' ').map(&:to_i)
s << 0

def fact(x)
  (1..x).inject(:*) || 1
end

def c(n, k)
  fact(n) / fact(k) / fact(n - k)
end

def sequence_idx(n, k, s)
  ans = 0
  (0..k - 1).each {|kk| (s[kk - 1] + 1..s[kk] - 1).each {|nn| ans += c(n - nn, k - kk - 1)}}
  ans
end

i = sequence_idx(n, k, s)
output.puts i
