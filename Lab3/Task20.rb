input = File.open('brackets2num2.in', 'r')
output = File.open('brackets2num2.out', 'w')
br = input.readline.strip
n = br.size / 2

size = n * 2
cnt = Array.new(size + 5) {Array.new(size + 5, 0)}
cnt[0][0] = 1
(1..size).each do |len|
  (0..size).each do |balance|
    cnt[len][balance] = cnt[len - 1][balance - 1] + cnt[len - 1][balance + 1]
  end
end

$ans = []
$balance = 0

k = 0

(0..size - 1).each do |i|
  suf1 = cnt[size - 1 - i][$balance + 1] * (1 << ((size - 2 - $balance - i) / 2))
  suf2 = cnt[size - 1 - i][$balance - 1] * (1 << ((size - $balance - i) / 2))

  matching = 0
  ans_balance = -1
  (i - 1).downto(0).each do |j|
    if br[j] == '(' or br[j] == '['
      ans_balance += 1
      if ans_balance == 0
        matching = j
        break
      end
    else
      ans_balance -= 1
    end
  end

  if $balance == 0
    if br[i] == '[' # k >= suf1
      k += suf1
    end
  elsif $balance == size - i
  else
    prev = br[matching]
    if prev == '('
      if br[i] == '[' # k >= suf1 + suf2
        k += suf1 + suf2
      elsif br[i] == ')' # k >= suf1
        k += suf1
      end
    else
      if br[i] == ']' # k >= suf1 * 2
        k += suf1 * 2
      elsif br[i] == '[' # k >= suf1
        k += suf1
      end
    end
  end

  (br[i] == '(' or br[i] == '[') ? $balance += 1 : $balance -= 1
end

output.puts k