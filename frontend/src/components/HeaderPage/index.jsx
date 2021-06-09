import { faChartLine } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import styled from 'styled-components'
import Navbar from '../Navbar'

const Title = styled.h1`
  color: var(--color-text-h1);
  font-size: 1.8rem;
`

const LogoApp = styled.div`
  display: flex;
  flex-flow: row nowrap;
  width: 75vw;
`

const Header = styled.header`
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  width: 100vw;
  box-shadow: var(--element-outer-shadow);
`
const HeaderPage = () => {
  return (
    <Header>
      <LogoApp>
        <FontAwesomeIcon icon={faChartLine} className='icon' />
        <Title> My Invest </Title>
      </LogoApp>
      <Navbar />
    </Header>
  )
}
export default HeaderPage
