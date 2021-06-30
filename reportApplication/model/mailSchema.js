const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const MailSchema = new Schema({
    to: {
        type: String,
        required: true,
    },
    cc: {
        type: String,
        required: false,
    },
    subject: {
        type: String,
        required: true,
    },
    html: {
        type: String,
        required: true,
    },
    date: {
        type: String,
        default: Date.now,
    },
});

module.exports = Mail = mongoose.model('mail', MailSchema);