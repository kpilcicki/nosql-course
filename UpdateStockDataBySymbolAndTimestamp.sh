curl -X PUT  http://localhost:8082/stockDataBySymbol   -H 'Cache-Control: no-cache'   -H 'Content-Type: application/json'   -d '{
  "symbol": "AAPL",
  "timestampData": "2019-04-12T04:04:04+0000",
  "open": "1203.45",
  "high": "244.27",
  "low": "-22.00",
  "close": "191.21",
  "volume": "196123"
}'