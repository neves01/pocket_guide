FROM golang:alpine AS builder

WORKDIR /app

COPY hello.go /app

RUN go env -w GO111MODULE=auto

RUN GOOS=linux GOARCH=amd64 go build -ldflags="-w -s" -o /app/hello

#

FROM scratch

COPY --from=builder /app/hello /go/bin/hello

ENTRYPOINT ["/go/bin/hello"]