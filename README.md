# Encryption_Programs

| Cipher | Encrypt | Decrypt | Break |
| ------ | :-----: | :-----: | :---: |
| Caesar |   YES   |   YES   |  YES  |
| Affine |   NO    |   NO    |  NO   |
| ROT13  |   NO    |   NO    |  NO   |
| Atbash |   NO    |   NO    |  NO   |
| Enigma |   NO    |   NO    |  NO   |
| Lorenz |   NO    |   NO    |  NO   |

## Caesar Cipher

### Substitution Cipher

Each letter of a given text is replaced by a letter some fixed number of positions down the alphabet.

This code encrypts, decrypts based on the offset number provided.

It can also break encrypted messages by utilizing the chi square formula to calculate the most likely offset number and translate the message.
