document.getElementById('appointmentForm').addEventListener('submit', function(e) {
    // 1. फॉर्म का पुराना डिफ़ॉल्ट पेज रीलोड रोकना
    e.preventDefault(); 

    // 2. इनपुट फील्ड्स से वैल्यूज बाहर निकालना
    const patientName = document.getElementById('pName').value;
    const doctorName = document.getElementById('dName').value;
    const appointmentDate = document.getElementById('apptDate').value;
    const msgDiv = document.getElementById('msg');

    msgDiv.innerText = "Processing...";
    msgDiv.style.color = "#555";

    // 3. डेटा को URL प्रारूप (Form URL Encoded) में पैक करना
    const params = new URLSearchParams();
    params.append('patientName', patientName);
    params.append('doctorName', doctorName);
    params.append('appointmentDate', appointmentDate);

    // 4. बैकएंड सर्वलेट REST API को हिट करना (Asynchronous Request)
    fetch('/api/book-appointment', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: params
    })
    // 5. सर्वर से आए JSON रिस्पॉन्स को पढ़ना
    .then(res => {
        if (!res.ok) {
            throw new Error("Server responded with an error status");
        }
        return res.json();
    }) 
    .then(data => {
        // 6. बिना वेबपेज रीलोड किए स्क्रीन पर मैसेज चमकाना
        if(data.status === 'success') {
            msgDiv.style.color = 'green';
            msgDiv.innerText = data.message;
            document.getElementById('appointmentForm').reset(); // फॉर्म क्लियर करना
        } else {
            msgDiv.style.color = 'red';
            msgDiv.innerText = data.message;
        }
    })
    .catch(err => {
        console.error("Error Detail:", err);
        msgDiv.style.color = 'red';
        msgDiv.innerText = 'Server Connect Error! Check Tomcat Settings.';
    });
});
