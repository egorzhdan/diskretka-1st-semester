input = File.open('nextbrackets.in', 'r')
output = File.open('nextbrackets.out', 'w')
$b = input.readline.strip

def next_brackets
  balance = 0
  i = $b.size - 1
  while i > 0 do
    ($b[i] == ')') ? balance += 1 : balance -= 1

    if $b[i] == '(' and balance > 0
      $b[i] = ')'
      balance -= 1
      (i + 1..$b.size - 1).each do |j|
        if $b.size - j > balance
          $b[j] = '('
          balance += 1
        else
          $b[j] = ')'
          balance -= 1
        end
      end
      # if balance != 0
      #   puts 'AAA'
      #   raise RuntimeError
      # end
      # return $b
      return 0
    end
    i -= 1
  end
end

r = next_brackets
output.puts (r.nil? ? '-' : $b)
