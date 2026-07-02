package consumer

import (
	"context"
	"encoding/json"
	"fmt"

	"sms-consumer/model"

	"github.com/segmentio/kafka-go"
	"go.mongodb.org/mongo-driver/mongo"
)

func StartConsumer(collection *mongo.Collection) {

	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers: []string{"localhost:9092"},
		Topic:   "sms-events",
		GroupID: "sms-consumer-group",
	})

	fmt.Println("Kafka Consumer Started...")

	for {

		message, err := reader.ReadMessage(context.Background())
		if err != nil {
			fmt.Println(err)
			continue
		}

		var event model.SmsEvent

		err = json.Unmarshal(message.Value, &event)
		if err != nil {
			fmt.Println(err)
			continue
		}

		_, err = collection.InsertOne(context.Background(), event)
		if err != nil {
			fmt.Println(err)
			continue
		}

		fmt.Println("Saved:", event.PhoneNumber)
	}
}