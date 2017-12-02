N, K = File.open('choose.in', 'r').readline.split(' ').map(&:to_i)
$out = File.open('choose.out','w')

$res = Array.new(K)

def go(i, last)
  if i == K
    $out.puts $res.join(' ')
  else
    (last + 1..N).each do |nxt|
      $res[i] = nxt
      go(i + 1, nxt)
    end
  end
end

go(0, 0)
