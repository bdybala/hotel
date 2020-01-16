import Cookies from 'universal-cookie';
import {Component} from "react";

class CookieManager extends Component {
  static _cookieName = 'login';

  static getLogin() {
    const cookies = new Cookies();
    return cookies.get(this._cookieName);
  }

  static setLogin(login) {
    const cookies = new Cookies();
    cookies.set(this._cookieName, login, {path: '/'});
  }

  static clearLogin() {
    const cookies = new Cookies();
    cookies.remove(this._cookieName)
  }

  static isLogged() {
    const cookies = new Cookies();
    return cookies.get(this._cookieName) !== undefined;
  }
}

export default CookieManager;