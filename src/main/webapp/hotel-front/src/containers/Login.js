import React, {Component} from 'react';
import LoginPanel from "./LoginPanel";
import CookieManager from "../CookieManager";

class Login extends Component {

  constructor(props) {
    super(props);

    this.login = this.login.bind(this);
  }

  login(credentials) {
    console.log(credentials);

    CookieManager.setLogin(credentials);
    // todo login
  }

  render() {
    return (
        <div>
          Logowanie
          <LoginPanel login={this.login}/>
        </div>
    )
  }
}

export default Login;