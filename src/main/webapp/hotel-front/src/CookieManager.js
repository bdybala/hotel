import Cookies from 'universal-cookie';
import {Component} from "react";

class CookieManager extends Component {
  static _cookieName = 'login';

  static getLoggedUser() {
    const cookies = new Cookies();
    return cookies.get(this._cookieName);
  }

  static setLoggedUser(loggedUser) {
    const cookies = new Cookies();
    cookies.set(this._cookieName, loggedUser, {path: '/'});
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