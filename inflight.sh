while true; do
  printf "\rinflight: \033[K%s" "$(curl -s http://localhost:9090/api/v1/internal/inflight)"
  sleep 0.2
done