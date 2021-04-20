import { TOKEN_KEY } from '../constants';

export default function authHeader() {
    const token = localStorage.getItem(TOKEN_KEY);

    if (token) {
        return { Authorization: 'Bearer ' + token };
    } else {
        return {};
    }
}