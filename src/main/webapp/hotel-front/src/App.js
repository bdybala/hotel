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
                    <NavItem eventKey={1}> O stronie</NavItem>
                  </LinkContainer>
                  <LinkContainer to="/availability">
                    <NavItem eventKey={2}> Dostepność</NavItem>
                  </LinkContainer>
                  {/*<LinkContainer to="/rooms">*/}
                  {/*  <NavItem eventKey={3}> Pokoje</NavItem>*/}
                  {/*</LinkContainer>*/}
                  <NavDropdown eventKey={4} title="Zarządzanie" id="basic-nav-dropdown">
                    <LinkContainer to="/manage/users">
                      <MenuItem eventKey={4.1}>Użytkownicy</MenuItem>
                    </LinkContainer>
                    <LinkContainer to="/manage/rooms">
                      <MenuItem eventKey={4.2}>Pokoje</MenuItem>
                    </LinkContainer>
                    <LinkContainer to="/manage/reservations">
                      <MenuItem eventKey={4.3}>Rezerwacje</MenuItem>
                    </LinkContainer>
                  </NavDropdown>
                </Nav>
                <Nav pullRight>
                  <LinkContainer to="/register">
                    <NavItem eventKey={5} href="#">Rejestracja</NavItem>
                  </LinkContainer>
                  <LinkContainer to="/login">
                    <NavItem eventKey={6} href="#">Zaloguj</NavItem>
                  </LinkContainer>
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