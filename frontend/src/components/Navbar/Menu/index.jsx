import styled from 'styled-components'

const StyledMenu = styled.ul`
  display: flex;
  flex-flow: row nowrap;
  li {
    padding: 10px 20px;
  }

  @media (max-width: 768px) {
    flex-flow: column nowrap;
    background-color: var(--color-text-h1);
    position: fixed;
    transform: ${({ open }) => (open ? 'translateX(0)' : 'translateX(100%)')};
    top: 0;
    right: 0;
    height: 100vh;
    width: 250px;
    padding-top: 3rem;
    transition: 0.3s ease-in-out;
  }
`

const StyledMenuItem = styled.li``

const Menu = ({ open }) => {
  return (
    <>
      <StyledMenu open={open}>
        <StyledMenuItem>Cadastrar</StyledMenuItem>
        <StyledMenuItem>Gráficos</StyledMenuItem>
        <StyledMenuItem>Sobre</StyledMenuItem>
      </StyledMenu>
    </>
  )
}

export default Menu
