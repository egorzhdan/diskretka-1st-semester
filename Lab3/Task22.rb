input = File.open('part2num.in', 'r')
output = File.open('part2num.out', 'w')
p = input.readline.split('+').map(&:to_i)
n = p.inject(0, :+) || 0

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

def partition_idx(p, last=1)
  n = p.inject(0, :+) || 0
  if n == 0
    return 0
  end

  k = 0
  (last..n).each do |cur|
    cnt_starting_with = cur == n ? $cnt[cur][n][n] : $cnt[cur][n][n] - $cnt[cur + 1][n][n]
    if p[0] > cur
      k += cnt_starting_with
    else
      p.shift
      k += partition_idx(p, cur)
      return k
    end
  end

  puts 'GGWP #n #k'
  0
end

ans = partition_idx(p)

output.puts ans
