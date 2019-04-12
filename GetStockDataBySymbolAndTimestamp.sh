curl -X GET  http://localhost:8082/stockDataBySymbolTimestamp   -H 'Cache-Control: no-cache'   -H 'Content-Type: application/json'   -d '{
  "symbol": "AAPL",
  "timestampData": "2019-04-12T04:21:22"
}'