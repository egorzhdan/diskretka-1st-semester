input = File.open('num2brackets2.in', 'r')
output = File.open('num2brackets2.out', 'w')
n, k = input.readline.split(' ').map(&:to_i)

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

def push(b)
  $ans.push b
  (b == '(' or b == '[') ? $balance += 1 : $balance -= 1
end

(0..size - 1).each do |i|
  suf1 = cnt[size - 1 - i][$balance + 1] * (1 << ((size - 2 - $balance - i) / 2))
  suf2 = cnt[size - 1 - i][$balance - 1] * (1 << ((size - $balance - i) / 2))

  matching = 0
  ans_balance = -1
  ($ans.size - 1).downto(0).each do |j|
    if $ans[j] == '(' or $ans[j] == '['
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
    if k >= suf1
      push '['
      k -= suf1
    else
      push '('
    end
  elsif $balance == size - i
    prev = $ans[matching]
    nxt = (prev == '(') ? ')' : ']'
    push nxt
  else
    prev = $ans[matching]
    if prev == '('
      if k >= suf1 + suf2
        push '['
        k -= suf1 + suf2
      elsif k >= suf1
        push ')'
        k -= suf1
      else
        push '('
      end
    else
      if k >= suf1 * 2
        push ']'
        k -= suf1 * 2
      elsif k >= suf1
        push '['
        k -= suf1
      else
        push '('
      end
    end
  end
end

output.puts $ans.join ''