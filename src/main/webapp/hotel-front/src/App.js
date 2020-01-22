import React, {Component} from 'react';
import {BrowserRouter as Router, NavLink, Redirect} from 'react-router-dom';
import {MenuItem, Nav, Navbar, NavDropdown, NavItem} from 'react-bootstrap';
import {LinkContainer} from 'react-router-bootstrap';

import Routes from './Routes';
import UserContext from './UserContext'

import './App.css';
import CookieManager from "./CookieManager";

class App extends Component {
  constructor(props) {
    super(props);

    this.loginUser = this.loginUser.bind(this);
    this.logoutUser = this.logoutUser.bind(this);

    this.state = {
      loggedUser: null,
      firstName: null,
      lastName: null,
      role: null,
      redirect: null,
    };
  }

  componentDidMount() {
    if (CookieManager.getLoggedUser()) {
      this.updateStateLoggedUser(CookieManager.getLoggedUser());
    }
  }

  loginUser(loggedUser) {
    this.updateStateLoggedUser(loggedUser);
    CookieManager.setLoggedUser(loggedUser);
  }

  updateStateLoggedUser(loggedUser) {
    this.setState({
      loggedUser: loggedUser,
      firstName: loggedUser.firstName,
      lastName: loggedUser.lastName,
      role: loggedUser.roleName,
      redirect: null,
    });
  }

  logoutUser() {
    this.setState({
      loggedUser: null,
      firstName: null,
      lastName: null,
      role: null,
      redirect: "/",
    });
    console.log('Logging out');
    CookieManager.clearLogin();
  }

  render() {
    return (
        <UserContext.Provider value={this.state.loggedUser}>
          <Router>
            {this.state.redirect && <Redirect to={this.state.redirect}/>}
            <div className="App container">
              <Navbar fluid collapseOnSelect>
                <Navbar.Header>
                  <Navbar.Brand>
                    <NavLink to="/">Hata ERP</NavLink>
                  </Navbar.Brand>
                </Navbar.Header>
                <Navbar.Toggle/>
                <Navbar.Collapse>
                  <Nav>
                    <LinkContainer to="/about">
                      <NavItem eventKey={1}> O hotelu</NavItem>
                    </LinkContainer>
                    {(this.state.role === 'ADMINISTRATOR' || this.state.role === 'GUEST') && <LinkContainer
                        to="/availability">
                      <NavItem eventKey={2}> Dostepność</NavItem>
                    </LinkContainer>
                    }
                    {(this.state.role === 'ADMINISTRATOR' || this.state.role === 'GUEST') && <LinkContainer
                        to="/manage/reservations">
                      <NavItem eventKey={3}> Rezerwacje</NavItem>
                    </LinkContainer>
                    }
                    {this.state.role === 'ADMINISTRATOR' && <NavDropdown
                        eventKey={4} title="Zarządzanie"
                        id="basic-nav-dropdown">
                      <LinkContainer to="/manage/users">
                        <MenuItem eventKey={4.1}>Użytkownicy</MenuItem>
                      </LinkContainer>
                      <LinkContainer to="/manage/rooms">
                        <MenuItem eventKey={4.2}>Pokoje</MenuItem>
                      </LinkContainer>
                      <LinkContainer to="/manage/visits">
                        <MenuItem eventKey={4.3}>Wizyty</MenuItem>
                      </LinkContainer>
                    </NavDropdown>
                    }
                  </Nav>
                  <Nav pullRight>
                    <NavItem
                        disabled>{this.state.firstName} {this.state.lastName}</NavItem>
                    {!this.state.loggedUser && <LinkContainer to="/register">
                      <NavItem eventKey={4} href="#">Rejestracja</NavItem>
                    </LinkContainer>
                    }
                    {!this.state.loggedUser && <LinkContainer to="/login">
                      <NavItem eventKey={5} href="#">Zaloguj</NavItem>
                    </LinkContainer>
                    }
                    {this.state.loggedUser &&
                    <NavItem eventKey={6} href="#"
                             onClick={() => this.logoutUser()}>Wyloguj</NavItem>
                    }
                  </Nav>
                </Navbar.Collapse>
              </Navbar>
              <Routes handleLogin={this.loginUser}/>
            </div>
          </Router>
        </UserContext.Provider>
    );
  }
}

export default App;