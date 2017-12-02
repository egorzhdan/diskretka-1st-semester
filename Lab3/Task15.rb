input = File.open('num2choose.in', 'r')
output = File.open('num2choose.out', 'w')
n, k, m = input.readline.split(' ').map(&:to_i)

def fact(x)
  (1..x).inject(:*) || 1
end

def c(n, k)
  fact(n) / fact(k) / fact(n - k)
end

def sequence(n, k, m)
  nxt = 1
  ans = []
  kk = k
  mm = m
  while kk > 0
    n -= 1
    cur_c = c(n, kk - 1)
    if mm < cur_c
      ans << nxt
      kk -= 1
    else
      mm -= cur_c
    end
    nxt += 1
  end
  ans
end

s = sequence(n, k, m)
output.puts s.join ' '
