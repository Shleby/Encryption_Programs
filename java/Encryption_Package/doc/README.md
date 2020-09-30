## Affine Cipher

## Atbash Cipher

## Caesar Cipher

### Substitution Cipher

Each letter of a given text is replaced by a letter some fixed number of positions down the alphabet.

This code encrypts, decrypts based on the offset number provided.

Encryption Formula : E_n(x) = (x+n) % 26

Decryption Formula : D_n(x) = (x-n) % 26

It can also break encrypted messages by utilizing the chi square formula to calculate the most likely offset number and translate the message.

Break formula : Σ(O_i - E_i)^2 / E_i

Where O_i is the actual value, and E_i is the expected value. Based on statistical data about English letter frequencies. It is known that in a text of 1000 letters of various English alphabet occur with about the certain frequencies.

## ROT13 Cipher
