const express = require('express');
const router = express.Router();
const nodemailer = require('nodemailer');

const transporter = nodemailer.createTransport({
    port: 465,
    host: "smtp.gmail.com",
    auth: {
        user: 'your_email',
        pass: 'your_email_password',
    },
    secure: true, // upgrades later with STARTTLS -- change this based on the PORT
});

router.post('/text-mail', (req, res) => {
    const {to, subject, text } = req.body;
    const mailData = {
        from: 'your_email',
        to: to,
        subject: subject,
        text: text,
        html: '<b>Hey there! </b><br> This is our first message sent with Nodemailer<br/>',
    };

    transporter.sendMail(mailData, (error, info) => {
        if (error) {
            return console.log(error);
        }
        res.status(200).send({ message: "Mail send", message_id: info.messageId });
    });
});


router.post('/attachments-mail', (req, res) => {
    const {to, subject, text } = req.body;
    const mailData = {
        from: 'your_email',
        to: to,
        subject: subject,
        text: text,
        html: '<b>Hey there! </b><br> This is our first message sent with Nodemailer<br/>',
        attachments: [
            {   // file on disk as an attachment
                filename: 'nodemailer.png',
                path: 'nodemailer.png'
            },
            {   // file on disk as an attachment
                filename: 'text_file.txt',
                path: 'text_file.txt'
            }
        ]
    };

    transporter.sendMail(mailData, (error, info) => {
        if (error) {
            return console.log(error);
        }
        res.status(200).send({ message: "Mail send", message_id: info.messageId });
    });
});

module.exports = router;