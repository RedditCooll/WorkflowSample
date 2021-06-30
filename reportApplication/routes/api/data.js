const express = require('express');
const router = express.Router();

const Mail = require('../../model/mailSchema');

router.post('/save', async(req, res) => {
     //x-www-form-urlencoded
    const {to, subject, html } = req.body;
    try {
        const mail = new Mail({
            to: to,
            subject: subject,
            html: html,
            data: Date.now
        });
        console.log('before save');
        let saveMail = await mail.save(); //when fail its goes to catch
        res.status(200).send(saveMail);
        console.log(saveMail); //when success it print.
        console.log('after save');
    } catch (err) {
        console.log('err' + err);
        res.status(500).send(err);
    }
});

router.post('/search', (req, res) => {
    const {to} = req.body;
    Mail.find({ 'to': to }, function (err, data) {
        if (err){
            console.log(err);
            res.status(500).send('failed!');
        }
        else{
            console.log("First function call : ", data);
            res.status(200).send(data);
        }
    }); 
});

router.post('/revise', (req, res) => {
    const {target_to, to, subject, html } = req.body;
    const mail = {
        to: to,
        subject: subject,
        html: html,
        data: Date.now
    }
    Mail.updateOne({to: target_to}, mail, function (err, data) {
        if (err){
            console.log(err);
            res.status(500).send('failed!');
        }
        else{
            console.log("First function call : ", data);
            res.status(200).send(data);
        }
    });
});

router.post('/delete', (req, res) => {
    const {to, subject, html } = req.body;
    const mail = {
        to: to,
        subject: subject,
        html: html,
        data: Date.now
    }
    Mail.deleteOne(mail, function (err, data) {
        if (err){
            console.log(err);
            res.status(500).send('failed!');
        }
        else{
            console.log("First function call : ", data);
            res.status(200).send(data);
        }
    });
});

module.exports = router;