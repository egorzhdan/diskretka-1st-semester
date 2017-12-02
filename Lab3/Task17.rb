input = File.open('num2brackets.in', 'r')
output = File.open('num2brackets.out', 'w')
n, k = input.readline.split(' ').map(&:to_i)

size = n * 2
cnt = Array.new(size + 5) {Array.new(size + 5, 0)}
cnt[0][0] = 1
(1..size).each do |len|
  (0..size).each do |balance|
    cnt[len][balance] = cnt[len - 1][balance - 1] + cnt[len - 1][balance + 1]
  end
end

ans = []
balance = 0
(0..size - 1).each do |i|
  suf = cnt[size - 1 - i][balance + 1]
  if k < suf
    ans.push '('
    balance += 1
  else
    k -= suf
    ans.push ')'
    balance -= 1
  end
end
output.puts ans.join ''