import React, { Component } from 'react';
import { BrowserRouter as Router, NavLink } from 'react-router-dom';
import { Nav, Navbar, NavItem, MenuItem, NavDropdown} from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

import Routes from './Routes';

import './App.css';

class App extends Component {
  render() {
    return (
        <Router>
          <div className="App container">
            <Navbar fluid collapseOnSelect>
              <Navbar.Header>
                <Navbar.Brand>
                  <NavLink to="/">Hata ERP</NavLink>
                </Navbar.Brand>
              </Navbar.Header>
              <Navbar.Toggle />
              <Navbar.Collapse>
                <Nav>
                  <LinkContainer to="/about">
                    <NavItem eventKey={2}> O stronie</NavItem>
                  </LinkContainer>
                  <LinkContainer to="/availability">
                    <NavItem eventKey={3}> Dostepność</NavItem>
                  </LinkContainer>
                  <LinkContainer to="/rooms">
                    <NavItem eventKey={4}> Pokoje</NavItem>
                  </LinkContainer>
                  <NavDropdown eventKey={5} title="Zarządzanie" id="basic-nav-dropdown">
                    <LinkContainer to="/manage/users">
                      <MenuItem eventKey={5.1}>Użytkownicy</MenuItem>
                    </LinkContainer>
                    <LinkContainer to="/manage/rooms">
                      <MenuItem eventKey={5.2}>Pokoje</MenuItem>
                    </LinkContainer>
                    <LinkContainer to="/manage/reservations">
                      <MenuItem eventKey={5.3}>Rezerwacje</MenuItem>
                    </LinkContainer>
                  </NavDropdown>
                </Nav>
                <Nav pullRight>
                  <NavItem eventKey={1} href="#">Zaloguj</NavItem>
                </Nav>
              </Navbar.Collapse>
            </Navbar>
            <Routes />
          </div>
        </Router>
    );
  }
}

export default App;