curl -X POST   http://localhost:8082/stockDataBySymbol   -H 'Cache-Control: no-cache'   -H 'Content-Type: application/json'   -d '{
  "symbol": "AAPL",
  "timestampData": "2019-04-12T04:21:22",
  "open": "166.21",
  "high": "244.27",
  "low": "142.00",
  "close": "191.21",
  "volume": "196123"
}'