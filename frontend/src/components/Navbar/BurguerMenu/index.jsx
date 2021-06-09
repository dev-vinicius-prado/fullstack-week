import { useState } from 'react'
import styled from 'styled-components'
import Menu from '../Menu'

const StyledBurguer = styled.div`
  height: 2rem;
  width: 2rem;
  position: fixed;
  top: 12px;
  right: 15px;
  display: none;
  z-index: 2;

  @media (max-width: 768px) {
    display: flex;
    flex-flow: column nowrap;
    justify-content: space-around;
  }

  & div {
    height: 0.25rem;
    width: 2rem;
    background-color: ${({ open }) =>
      open ? 'var(--color-text-primary)' : 'var(--color-text-h1)'};
    border-radius: 10px;
    transform-origin: 1px;
    transition: all 0.3s linear;

    &:nth-child(1) {
      transform: ${({ open }) => (open ? 'rotate(45deg)' : 'rotate(0)')};
    }

    &:nth-child(2) {
      transform: ${({ open }) => (open ? 'translateX(100%)' : 'translateX(0)')};
      opacity: ${({ open }) => (open ? 0 : 1)};
    }

    &:nth-child(3) {
      transform: ${({ open }) => (open ? 'rotate(-45deg)' : 'rotate(0)')};
    }
  }
`

const BurguerMenu = () => {
  const [open, setOpen] = useState(false)

  return (
    <>
      <StyledBurguer open={open} onClick={() => setOpen(!open)}>
        <div></div>
        <div></div>
        <div></div>
      </StyledBurguer>
      <Menu open={open} />
    </>
  )
}

export default BurguerMenu
