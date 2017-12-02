input = File.open('nextmultiperm.in', 'r')
output = File.open('nextmultiperm.out', 'w')
n = input.readline.to_i
p = input.readline.split(' ').map(&:to_i)

def next_perm(p)
  n = p.size
  (n - 2).downto(0).each do |i|
    if p[i] < p[i + 1]
      min = i + 1
      (i + 1..n - 1).each {|j| min = j if p[i] < p[j]}
      p[i], p[min] = p[min], p[i]

      (0..(n - 1 - i - 1) / 2).each {|d| p[i + 1 + d], p[n - 1 - d] = p[n - 1 - d], p[i + 1 + d]}
      return p
    end
  end
  nil
end


prv = next_perm(p) || Array.new(p.size, 0)
output.puts prv.join(' ')