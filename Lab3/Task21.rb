input = File.open('num2part.in', 'r')
output = File.open('num2part.out', 'w')
n, k = input.readline.split(' ').map(&:to_i)

$cnt = Array.new(n + 5) {Array.new(n + 5) {Array.new(n + 5, 0)}}
(0..n).each {|i| $cnt[i][0][0] = 1}

(1..n+1).each do |min|
  (0..n).each do |count|
    (min..n).each do |bound|
      (bound > count) ?
          $cnt[min][count][bound] = $cnt[min][count][count] :
          $cnt[min][count][bound] = $cnt[min][count - bound][bound] + $cnt[min][count][bound - 1]
    end
  end
end

def partition(n, k, last=1)
  if n == 0
    if k == 0
      return []
    else
      puts 'WTF n=' + n.to_s + ' k=' + k.to_s
      throw AssertionError.new
    end
  end

  (last..n).each do |cur|
    cnt_starting_with = cur == n ? $cnt[cur][n][n] : $cnt[cur][n][n] - $cnt[cur + 1][n][n]
    if k >= cnt_starting_with
      k -= cnt_starting_with
    else
      ans = [cur]
      ans += partition(n - cur, k, cur)
      return ans
    end
  end

  puts 'GGWP n=' + n.to_s + ' k=' + k.to_s
  throw AssertionError.new
end

ans = partition(n, k)

output.puts ans.join '+'
