const form = document.getElementById("smsForm");

form.addEventListener("submit", async function (e) {

    e.preventDefault();

    const userId = document.getElementById("userId").value;

    const phoneNumber = document.getElementById("phoneNumber").value;

    const message = document.getElementById("message").value;

    const responseDiv = document.getElementById("response");

    responseDiv.innerHTML = "Sending...";

    try {

        const response = await fetch(
            "http://localhost:8080/v1/sms/send",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify({
                    userId,
                    phoneNumber,
                    message
                })
            }
        );

        const data = await response.json();

        responseDiv.style.color = "green";

        responseDiv.innerHTML =
            `<p>${data.status}</p>
             <p>${data.message}</p>`;

    } catch (error) {

        responseDiv.style.color = "red";

        responseDiv.innerHTML =
            "Unable to connect to backend.";
    }

});