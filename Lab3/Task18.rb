input = File.open('brackets2num.in', 'r')
output = File.open('brackets2num.out', 'w')
b = input.readline.strip

cnt = Array.new(b.size + 5) {Array.new(b.size + 5, 0)}
cnt[0][0] = 1
(1..b.size).each do |len|
  (0..b.size).each do |balance|
    cnt[len][balance] = cnt[len - 1][balance - 1] + cnt[len - 1][balance + 1]
  end
end

ans = 0
balance = 0
(0..b.size - 1).each do |i|
  if b[i] == '('
    balance += 1
  else
    ans += cnt[b.size - 1 - i][balance + 1]
    balance -= 1
  end
end
output.puts ans
