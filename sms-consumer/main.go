package main

import (
	"fmt"
	"sms-consumer/consumer"
	"sms-consumer/database"
)

func main() {

	fmt.Println("Starting SMS Consumer...")

	collection, err := database.ConnectMongo()
	if err != nil {
		panic(err)
	}

	consumer.StartConsumer(collection)
}